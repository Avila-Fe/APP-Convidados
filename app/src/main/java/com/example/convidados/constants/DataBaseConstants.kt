package com.example.convidados.constants

class DataBaseConstants private constructor(){

    object GUEST{
        const val TABLE_NAME = "Guest"
        const val ID = "guestid"
        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}