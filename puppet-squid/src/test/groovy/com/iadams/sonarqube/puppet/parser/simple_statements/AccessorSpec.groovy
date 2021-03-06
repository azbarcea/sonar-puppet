/*
 * SonarQube Puppet Plugin
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Iain Adams and David RACODON
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.iadams.sonarqube.puppet.parser.simple_statements

import com.iadams.sonarqube.puppet.parser.GrammarSpec

import static com.iadams.sonarqube.puppet.api.PuppetGrammar.HASH_ARRAY_ACCESSES
import static org.sonar.sslr.tests.Assertions.assertThat

class AccessorSpec extends GrammarSpec {

  def setup() {
    setRootRule(HASH_ARRAY_ACCESSES)
  }

  def "simple array/hashes accessor"() {
    expect:
    assertThat(p).matches('$foo[1]')
    assertThat(p).matches('$foo[\'one\']')
  }

  def "negative integer array accessor"() {
    expect:
    assertThat(p).matches('$foo[-1]')
  }

  def "Nested arrays and hashes can be accessed by chaining indexes"() {
    expect:
    assertThat(p).matches('$foo[1][\'third\']')
    assertThat(p).matches('$foo[\'one\'][1]')
    assertThat(p).matches('$main_site[port][https]')
  }
}