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

import scala.collection.immutable._

/**
  * The type NodeSearch
  *
  * @author Peter Pilgrim
  */


object NodeSearch {
  def retrieveById(root: Node, id: String): Option[Node] = {

    if ( root.id == id) {
      return Option(root)
    }

    root match {
      case Empty => None
      case _ => {
        retrieveById(root.left, id).orElse( retrieveById( root.middle, id )).orElse( retrieveById(root.right, id))
      }
    }
  }


  def retrieveDescendents(node: Node): List[Node] = {

    def inorderTraversal( list: List[Node], node: Node): List[Node] = {
      node match {
        case Empty => Nil
        case _ => {
          List(node) ::: inorderTraversal(list, node.left) ::: inorderTraversal(list, node.middle) ::: inorderTraversal(list, node.right)
        }
      }
    }

    val result = inorderTraversal( List(), node )

    result match {
      case first :: rest => rest
      case _ => result
    }
  }

  def retrieveByTag(node: Node, tag: ContentTag): List[Node] = {
    def inorderSearch( list: List[Node], node: Node): List[Node] = {
      node match {
        case Empty => Nil
        case x:ContentNode => {
          (if ( x.tags.contains(tag)) List(node) else Nil) :::
          inorderSearch(list, node.left) ::: inorderSearch(list, node.middle) ::: inorderSearch(list, node.right)
        }
        case _ => Nil
      }
    }

    inorderSearch( List(), node )
  }


}
