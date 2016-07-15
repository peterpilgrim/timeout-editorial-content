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

import scala.collection.immutable.List

/**
  * Verifies the operation of the NodeSearchSpec
  *
  * @author Peter Pilgrim
  */

@RunWith(classOf[JUnitRunner])
class NodeSearchSpec extends FlatSpec with Matchers {

  def chineseTag = ContentTag("chinese")
  def restaurantTag = ContentTag("restaurant")

  def hongKongTag = ContentTag("hongkong")
  def szechuanTag = ContentTag("szechuan")

  val chineseFilms = ContentNode("chinese", tags = Set(chineseTag,szechuanTag))
  val comedy = ContentNode("comedy")
  val action = ContentNode("action")

  val films = ContentNode("films", left = chineseFilms, middle = comedy, right = action)

  val shows = ContentNode("shows", left = ContentNode("theatre"), middle = films )

  val music = ContentNode("music", left = ContentNode("jazz"), middle = ContentNode("pop"), right = ContentNode("rock"))

  val chineseFood = ContentNode("chinese", tags = Set(chineseTag,szechuanTag))

  val restaurants = ContentNode("restaurants", left = chineseFood, middle = ContentNode("french"), right = ContentNode("italian"), tags = Set(restaurantTag))

  val categories = ContentNode("categories", left = shows, middle = music, right = restaurants )


//  println( categories)
//  println( categories.stringifyNodes() )


  "NodeSearch" should "retrieve node by id" in {

    val result = NodeSearch.retrieveById(categories, "action")

    printf(s"result = $result\n")

    result should be === Some(action)
  }


  "NodeSearch" should "not retrieve node by id" in {

    val result = NodeSearch.retrieveById(categories, "magic")

    printf(s"result = $result\n")

    result should be === None
  }

  "NodeSearch" should "find the descendents of a node" in {
    val descendents = NodeSearch.retrieveDescendents( films )

//    printf(s"descendents=$descendents\n")

    descendents should be === List(chineseFood,comedy,action)
  }

  "NodeSearch" should "find the descendents of a leaf node" in {
    val descendents = NodeSearch.retrieveDescendents( action )

    descendents should be === List()
  }

  "NodeSearch" should "find the nodes with content tag" in {
    val taggedNodes = NodeSearch.retrieveByTag( categories, chineseTag )

//    printf(s"taggedNodes=$taggedNodes\n")

    taggedNodes should be === List(chineseFood, chineseFilms)
  }

}
