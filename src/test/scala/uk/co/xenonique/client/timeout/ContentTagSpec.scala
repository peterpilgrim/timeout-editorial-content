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
  * Verifies the operation of the ContentTagSpec
  *
  * @author Peter Pilgrim
  */

@RunWith(classOf[JUnitRunner])
class ContentTagSpec extends FlatSpec with Matchers {
  "Tag" should "be buildable as an orphan" in {

    def chineseTag = ContentTag("chinese")
    chineseTag.name should be === "chinese"
    chineseTag.parent should be === None
  }

  "Tag" should "be buildable with parent" in {

    def chineseTag = ContentTag("chinese")
    chineseTag.name should be === "chinese"
    chineseTag.parent should be === None

    def frenchChineseTag = ContentTag("chinois", Some(chineseTag))
    frenchChineseTag.name should be === "chinois"
    frenchChineseTag.parent should be === Some(chineseTag)

  }

}
