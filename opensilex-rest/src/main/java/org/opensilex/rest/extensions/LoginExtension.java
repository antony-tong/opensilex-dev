//******************************************************************************
//                            LoginExtension.java
// OpenSILEX - Licence AGPL V3.0 - https://www.gnu.org/licenses/agpl-3.0.en.html
// Copyright © INRA 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.rest.extensions;

import com.auth0.jwt.JWTCreator;
import org.opensilex.rest.security.dal.UserModel;

/**
 * Extension interface for OpenSilex modules which want to add custom claims to
 * JWT token or acces them.
 *
 * @author Vincent Migot
 */
public interface LoginExtension {

    /**
     * Extension method to allow modules to add custom claims on User login in
     * JWT token. This token is available both on server and client sides.
     *
     * @param user Current user
     * @param tokenBuilder Token builder on which to add claims
     */
    public void login(UserModel user, JWTCreator.Builder tokenBuilder);
    
    public default void logout (UserModel user) {
        
    }
}