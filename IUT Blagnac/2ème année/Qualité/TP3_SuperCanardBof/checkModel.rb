# -*- coding: utf-8 -*-
# -------------------
# Author::    Jean-Michel Bruel  (mailto:jbruel@gmail.com)
# Copyright:: Copyright (c) 2014 JMB
# License::   Distributes under the same terms as Ruby
# -------------------

require "minitest/autorun"
MODEL_NAME = "model.uml"


class TestGeneratedModel < MiniTest::Unit::TestCase

  #------------ General tests about plantUML
  def test_generated_model_exists
    assert_equal(true, File.exists?(MODEL_NAME))
  end

  def test_generated_model_is_plantuml
    assert_equal(true, File.readlines(MODEL_NAME).grep(/@startuml/).any?)
  end

  def test_generated_model_exists
    assert_equal(true, File.exists?(MODEL_NAME))
  end

  #------------ Specific tests about expected content
  def test_class_Canard_is_abstract
    assert_equal(true, File.readlines(MODEL_NAME).grep(/abstract Canard/).any?)
  end


end
