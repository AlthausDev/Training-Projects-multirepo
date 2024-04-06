package com.example.recyclerviewexample.adapter

import com.example.recyclerviewexample.Item

class ItemProvider {
    companion object{
      val itemList = listOf<Item>(
          Item (
              "valor1",
              "valor2"
          ),

          Item (
              "valor1",
              "valor2"
          )
      )
    }
}