/*
 *  (c)      Copyright 2003, 2004, 2005 Hewlett-Packard Development Company, LP
 *    All rights reserved.
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
 *
 * RDFWriter.java
 *
 * Created on 29 July 2000, 07:28
 * 
 * Edited by Chris 18th October 2002; moved initialisation of nsMap into write so
 * that multiple uses of the same writer don't conflict.
 */

package com.hp.hpl.jena.rdf.model.impl;
import com.hp.hpl.jena.xmloutput.impl.Basic;

/** Writes out an XML serialization of a model.
 * @deprecated {@link com.hp.hpl.jena.xmloutput.impl.Basic }
 * @author  bwm
 * @version   Release='$Name: not supported by cvs2svn $' Revision='$Revision: 1.4 $' Date='$Date: 2005-02-21 12:14:50 $'
 */
public class RDFXMLWriter extends Basic {
}
