/**
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

package com.hp.hpl.jena.tdb;

import java.io.BufferedOutputStream ;
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.IOException ;
import java.io.OutputStream ;

import org.apache.jena.atlas.io.IO ;
import org.apache.jena.atlas.logging.Log ;
import org.apache.jena.riot.out.NQuadsWriter ;

import com.hp.hpl.jena.query.Dataset ;
import com.hp.hpl.jena.query.ReadWrite ;
import com.hp.hpl.jena.tdb.base.file.Location ;
import com.hp.hpl.jena.tdb.transaction.DatasetGraphTxn ;

/**
 * Backup a database.
 */

public class TDBBackup
{
    public static void backup(Location location, String backupfile)
    {
        try
        {
            OutputStream out = new FileOutputStream(backupfile) ;
            out = new BufferedOutputStream(out) ;
            backup(location, out) ;
            out.close() ;
        } 
        catch (FileNotFoundException e)
        {
            Log.warn(TDBBackup.class, "File not found: "+backupfile) ;
            throw new TDBException("File not found: "+backupfile) ;
        } 
        catch (IOException e)
        { IO.exception(e) ; }
        
    }
    
    public static void backup(Location location, OutputStream backupfile)
    {
        Dataset ds = TDBFactory.createDataset(location) ;
        StoreConnection sConn = StoreConnection.make(location) ;
        DatasetGraphTxn dsg = sConn.begin(ReadWrite.READ, "backup") ;
        NQuadsWriter.write(backupfile, dsg) ;
        dsg.end();
    }
}
