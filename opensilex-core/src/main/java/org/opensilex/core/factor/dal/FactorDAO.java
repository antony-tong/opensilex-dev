/*
 * ******************************************************************************
 *                                     FactorDAO.java
 *  OpenSILEX
 *  Copyright © INRA 2019
 *  Creation date:  17 December, 2019
 *  Contact: arnaud.charleroy@inra.fr, anne.tireau@inrae.fr, pascal.neveu@inrae.fr
 * ******************************************************************************
 */
package org.opensilex.core.factor.dal;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.sparql.expr.Expr;
import org.opensilex.core.experiment.dal.ExperimentSearchDTO;
import org.opensilex.core.factor.api.FactorSearchDTO;
import org.opensilex.sparql.service.SPARQLQueryHelper;
import org.opensilex.sparql.service.SPARQLService;
import org.opensilex.sparql.utils.OrderBy;
import org.opensilex.utils.ListWithPagination;

/**
 * 
 * @author Arnaud Charleroy
 */
public class FactorDAO {

    protected final SPARQLService sparql;

    public FactorDAO(SPARQLService sparql) {
        this.sparql = sparql;
    }

    public FactorModel create(FactorModel instance) throws Exception {
        sparql.create(instance);
        return instance;
    }

    public FactorModel update(FactorModel instance) throws Exception {
        sparql.update(instance);
        return instance;
    }

    public void delete(URI instanceURI) throws Exception {
        sparql.delete(FactorModel.class, instanceURI);
    }

    public FactorModel get(URI instanceURI) throws Exception {
        return sparql.getByURI(FactorModel.class, instanceURI, null);
    }

    public ListWithPagination<FactorModel> search(FactorSearchDTO factorSearchDTO, List<OrderBy> orderByList, Integer page, Integer pageSize) throws Exception {
        return sparql.searchWithPagination(
                FactorModel.class,
                null,
                (SelectBuilder select) -> {
                    // TODO implements filters
                    appendFilters(factorSearchDTO, select);
                },
                orderByList,
                page,
                pageSize
        );
    }
    
    /**
     * Append FILTER or VALUES clause on the given {@link SelectBuilder} for each non-empty simple attribute ( not a {@link List} from the {@link ExperimentSearchDTO}
     *
     * @param searchDTO a search DTO which contains all attributes about an {@link FactorModel} search
     * @param select search query
     * @throws java.lang.Exception can throw an exception
     * @see SPARQLQueryHelper the utility class used to build Expr
     */
    protected void appendFilters(FactorSearchDTO searchDTO, SelectBuilder select) throws Exception {

        List<Expr> exprList = new ArrayList<>(); 
        
        // build regex filters
        if (searchDTO.getAlias()!= null) {
            exprList.add(SPARQLQueryHelper.regexFilter(FactorModel.ALIAS_FIELD, searchDTO.getAlias()));
        }

        for (Expr filterExpr : exprList) {
            select.addFilter(filterExpr);
        }
    }
    
}
