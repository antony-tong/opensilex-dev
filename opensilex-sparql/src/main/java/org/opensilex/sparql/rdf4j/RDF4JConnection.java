//******************************************************************************
// OpenSILEX - Licence AGPL V3.0 - https://www.gnu.org/licenses/agpl-3.0.en.html
// Copyright © INRA 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.sparql.rdf4j;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.jena.arq.querybuilder.AskBuilder;
import org.apache.jena.arq.querybuilder.ConstructBuilder;
import org.apache.jena.arq.querybuilder.DescribeBuilder;
import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.arq.querybuilder.UpdateBuilder;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.opensilex.sparql.exceptions.SPARQLException;
import org.opensilex.sparql.service.SPARQLConnection;
import org.opensilex.sparql.service.SPARQLResult;
import org.opensilex.sparql.service.SPARQLStatement;
import org.opensilex.sparql.exceptions.SPARQLQueryException;
import org.opensilex.sparql.exceptions.SPARQLTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vincent
 */
public class RDF4JConnection implements SPARQLConnection {

    private final static Logger LOGGER = LoggerFactory.getLogger(RDF4JConnection.class);
    
    private final RepositoryConnection rdf4JConnection;

    public RDF4JConnection(RepositoryConnection rdf4JConnection) {
        this.rdf4JConnection = rdf4JConnection;
        LOGGER.debug("Acquire RDF4J sparql connection: " + this.rdf4JConnection.hashCode());

    }

    @Override
    public void shutdown() throws Exception {
        LOGGER.debug("Release RDF4J sparql connection: " + this.rdf4JConnection.hashCode());
        this.rdf4JConnection.close();
    }

    @Override
    public boolean executeAskQuery(AskBuilder ask) throws SPARQLQueryException {
        BooleanQuery askQuery = rdf4JConnection.prepareBooleanQuery(QueryLanguage.SPARQL, ask.buildString());
        return askQuery.evaluate();
    }

    @Override
    public List<SPARQLStatement> executeDescribeQuery(DescribeBuilder describe) throws SPARQLQueryException {
        GraphQuery describeQuery = rdf4JConnection.prepareGraphQuery(QueryLanguage.SPARQL, describe.buildString());
        GraphQueryResult results = describeQuery.evaluate();

        return statementsToSPARQLResultList(results);
    }

    @Override
    public List<SPARQLStatement> executeConstructQuery(ConstructBuilder construct) throws SPARQLQueryException {
        GraphQuery constructQuery = rdf4JConnection.prepareGraphQuery(QueryLanguage.SPARQL, construct.buildString());
        GraphQueryResult results = constructQuery.evaluate();

        return statementsToSPARQLResultList(results);
    }

    @Override
    public List<SPARQLResult> executeSelectQuery(SelectBuilder select, Consumer<SPARQLResult> resultHandler) throws SPARQLQueryException {
        TupleQuery selectQuery = rdf4JConnection.prepareTupleQuery(QueryLanguage.SPARQL, select.buildString());
        TupleQueryResult results = selectQuery.evaluate();

        return bindingSetsToSPARQLResultList(results, resultHandler);
    }

    @Override
    public void executeUpdateQuery(UpdateBuilder update) throws SPARQLQueryException {
        Update updateQuery = rdf4JConnection.prepareUpdate(QueryLanguage.SPARQL, update.buildRequest().toString());
        updateQuery.execute();
    }

    @Override
    public void executeDeleteQuery(UpdateBuilder update) throws SPARQLQueryException {
        Update updateQuery = rdf4JConnection.prepareUpdate(QueryLanguage.SPARQL, update.buildRequest().toString());
        updateQuery.execute();
    }

    @Override
    public void startTransaction() throws SPARQLTransactionException {
        rdf4JConnection.begin();
    }

    @Override
    public void commitTransaction() throws SPARQLTransactionException {
        rdf4JConnection.commit();
    }

    @Override
    public void rollbackTransaction() throws SPARQLTransactionException {
        rdf4JConnection.rollback();
    }

    @Override
    public void clearGraph(URI graph) throws SPARQLQueryException {
        rdf4JConnection.clear(SimpleValueFactory.getInstance().createIRI(graph.toString()));
    }

    @Override
    public void renameGraph(URI oldGraphURI, URI newGraphURI) throws SPARQLException {

        try {
            String moveQuery = "MOVE <" + oldGraphURI + "> TO <" + newGraphURI + ">";
            rdf4JConnection.prepareUpdate(QueryLanguage.SPARQL, moveQuery).execute();

        } catch (UpdateExecutionException | RepositoryException | MalformedQueryException e) {
            throw new SPARQLException(e);
        }
    }

    @Override
    public void clear() throws SPARQLQueryException {
        rdf4JConnection.clear();
    }

    private List<SPARQLStatement> statementsToSPARQLResultList(QueryResult<Statement> queryResults) {
        List<SPARQLStatement> resultList = new ArrayList<>();

        while (queryResults.hasNext()) {
            Statement result = queryResults.next();
            resultList.add(new RDF4JStatement(result));
        }

        return resultList;
    }

    private List<SPARQLResult> bindingSetsToSPARQLResultList(QueryResult<BindingSet> queryResults, Consumer<SPARQLResult> resultHandler) {
        List<SPARQLResult> resultList = new ArrayList<>();

        while (queryResults.hasNext()) {
            RDF4JResult result = new RDF4JResult(queryResults.next());
            if (resultHandler != null) {
                resultHandler.accept(result);
            }

            resultList.add(result);
        }

        return resultList;
    }

    @Deprecated
    public RepositoryConnection getRepositoryConnectionImpl() {
        return rdf4JConnection;
    }
}
