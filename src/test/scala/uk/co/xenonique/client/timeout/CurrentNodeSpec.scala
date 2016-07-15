/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/

package uk.co.xenonique.client.timeout


import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner


/**
  * Verifies the operation of the CurrentNodeSpec
  *
  * @author Peter Pilgrim
  */

@RunWith(classOf[JUnitRunner])
class CurrentNodeSpec extends FlatSpec with Matchers {
  "Content nodes" should "build with no leaves" in {

    val p1 = ContentNode( "categories" )
    println(p1.stringifyNodes())
    p1.stringifyNodes() should be === "Node(categories,Empty,Empty,Empty)"
  }


  "Content nodes" should "build with 1 leaf" in {
    val p1 = ContentNode( "categories", left = ContentNode( "shows") )
    println(p1.stringifyNodes())
    p1.stringifyNodes() should be === "Node(categories,Node(shows,Empty,Empty,Empty),Empty,Empty)"

  }

  "Content nodes" should "build with 2 leaves" in {
    val p1 = ContentNode( "categories", left = ContentNode( "shows"), middle = ContentNode("music") )
    println(p1.stringifyNodes())
    p1.stringifyNodes() should be === "Node(categories,Node(shows,Empty,Empty,Empty),Node(music,Empty,Empty,Empty),Empty)"

  }

  "Content nodes" should "build with 3 leaves" in {
    val p1 = ContentNode( "categories", left = ContentNode( "shows"), middle = ContentNode("music"), right = ContentNode("restaurants") )
    println(p1.stringifyNodes())
    p1.stringifyNodes() should be === "Node(categories,Node(shows,Empty,Empty,Empty),Node(music,Empty,Empty,Empty),Node(restaurants,Empty,Empty,Empty))"
  }

  "Content nodes" should "build with arbitary tags" in {
    val p1 = ContentNode( "starwars", tags = Set( ContentTag("book"), ContentTag("movie"), ContentTag("sci-fi") ))

    println(p1.toString())
    p1.stringifyNodes() should be === "Node(starwars,Empty,Empty,Empty)"

    p1.tags.size should be === 3
  }

}
