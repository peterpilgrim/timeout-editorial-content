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

trait Node {
  val id: String
  val left: Node
  val middle: Node
  val right: Node

  def stringifyNodes(): String = {
    val builder = StringBuilder.newBuilder
    builder.append("Node(")
    builder.append(id)
    builder.append(",")
    builder.append(if (left != null) left.stringifyNodes() else "null")
    builder.append(",")
    builder.append(if (middle != null) middle.stringifyNodes() else "null")
    builder.append(",")
    builder.append(if (right != null) right.stringifyNodes() else "null")
    builder.append(")")
    builder.toString()
  }
}

case object Empty extends Node {
  val id = "Empty"
  val left = null
  val middle = null
  val right = null

  override def stringifyNodes(): String = "Empty"
}

case class ContentNode(override val id: String,
                       override val left: Node = Empty,
                       override val middle: Node = Empty,
                       override val right: Node = Empty,
                       var tags: Set[Tag] = Set()) extends Node



