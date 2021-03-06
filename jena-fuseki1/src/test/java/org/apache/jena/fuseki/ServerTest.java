/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.fuseki;

import java.io.IOException ;
import java.net.ServerSocket ;
import java.util.Arrays ;

import org.apache.jena.fuseki.server.DatasetRegistry ;
import org.apache.jena.graph.Graph ;
import org.apache.jena.graph.Node ;
import org.apache.jena.graph.NodeFactory ;
import org.apache.jena.rdf.model.Model ;
import org.apache.jena.rdf.model.ModelFactory ;
import org.apache.jena.sparql.core.DatasetGraph ;
import org.apache.jena.sparql.core.DatasetGraphFactory ;
import org.apache.jena.sparql.modify.request.Target ;
import org.apache.jena.sparql.modify.request.UpdateDrop ;
import org.apache.jena.sparql.sse.SSE ;
import org.apache.jena.update.Update ;
import org.apache.jena.update.UpdateExecutionFactory ;
import org.apache.jena.update.UpdateProcessor ;

/** Manage a server for testing.
 * Example for one server per test suite: 
 * <pre>
    \@BeforeClass public static void beforeClass() { ServerTest.allocServer() ; }
    \@AfterClass  public static void afterClass()  { ServerTest.freeServer() ; }
    \@Before      public void beforeTest()         { ServerTest.resetServer() ; }
    </pre>
 */
public class ServerTest
{
    // Abstraction that runs a SPARQL server for tests.
    
    // Different to the Fuseki2 test ports.
    public static final int port             = choosePort(3635, 3634, 3653, 3652, 103635, 103634, 103653, 103652) ;
    public static final String urlRoot       = "http://localhost:"+port+"/" ;
    public static final String datasetPath   = "/dataset" ;
    public static final String serviceUpdate = "http://localhost:"+port+datasetPath+"/update" ; 
    public static final String serviceQuery  = "http://localhost:"+port+datasetPath+"/query" ; 
    public static final String serviceREST   = "http://localhost:"+port+datasetPath+"/data" ; 
    
    public static final String gn1       = "http://graph/1" ;
    public static final String gn2       = "http://graph/2" ;
    public static final String gn99      = "http://graph/99" ;
    
    public static final Node n1          = NodeFactory.createURI("http://graph/1") ;
    public static final Node n2          = NodeFactory.createURI("http://graph/2") ;
    public static final Node n99         = NodeFactory.createURI("http://graph/99") ;
    
    public static final Graph graph1     = SSE.parseGraph("(base <http://example/> (graph (<x> <p> 1)))") ;
    public static final Graph graph2     = SSE.parseGraph("(base <http://example/> (graph (<x> <p> 2)))") ;
    
    public static final Model model1     = ModelFactory.createModelForGraph(graph1) ;
    public static final Model model2     = ModelFactory.createModelForGraph(graph2) ;
    
    @SuppressWarnings("deprecation")
    private static EmbeddedFusekiServer1 server = null ;
    
    // reference count of start/stop server
    private static int countServer = 0 ; 
    
    static public void allocServer() {
        if ( countServer == 0 )
            setupServer() ;
        countServer++ ;
    }
    
    static public void freeServer() {
        if ( countServer >= 0 ) {
            countServer -- ;
            if ( countServer == 0 )
                teardownServer() ;
        }
    }
    
    @SuppressWarnings("deprecation")
    protected static void setupServer() {
        DatasetGraph dsg = DatasetGraphFactory.create() ;
        server = EmbeddedFusekiServer1.create(port, dsg, datasetPath) ;
        server.start() ;
    }
    
    @SuppressWarnings("deprecation")
    protected static void teardownServer() {
        DatasetRegistry.get().clear() ;
        if ( server != null )
            server.stop() ;
        server = null ;
    }
    public static void resetServer() {
        Update clearRequest = new UpdateDrop(Target.ALL) ;
        UpdateProcessor proc = UpdateExecutionFactory.createRemote(clearRequest, ServerTest.serviceUpdate) ;
        proc.execute() ;
    }
    
    // Imperfect probing for a port.
    // There is a race condition on finding a free port and using it in the tests. 
    private static int choosePort(int... ports) {
        for (int port : ports) {
            try {
                @SuppressWarnings("resource")
                ServerSocket s = new ServerSocket(port) ;
                s.close();
                return s.getLocalPort() ; // OK to call after close.
            } catch (IOException ex) { 
                continue;
            }
        }
        throw new FusekiException("Failed to find a port in :"+Arrays.asList(ports)) ;
    }
}
