/*
 *  (c) Copyright 2001, 2002, 2003, 2004, 2005 Hewlett-Packard Development Company, LP
 *  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
 * * $Id: TokenPipe.java,v 1.11 2005-02-21 12:09:17 andy_seaborne Exp $
   
   AUTHOR:  Jeremy J. Carroll
*/
/*
 * TokenPipe.java
 *
 * Created on June 22, 2001, 12:32 AM
 */

package com.hp.hpl.jena.rdf.arp;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;


/**
 *
 * @author  jjc
 
 */
abstract class TokenPipe implements TokenManager {

	//final List pipe = createPipe();
	private Token last;
	/** Creates new TokenPipe */
	TokenPipe() {
	}

	//abstract List createPipe();
	abstract void putNextToken(Token t) throws SAXParseException;
	abstract public Token  getNextToken();
	void setLast(Token t) {
		last = t;
	}

    Locator getLocator() {
        //if ( pipe.size() > 0 ) {
        //    return ((Token)pipe.get(getPosition()-1)).location;
        //} else 
        if ( last != null )
          return  last.location;
        else 
          return null;
    }


	/**
	 * @return Returns the position.
	 */
	//abstract int getPosition();
}
