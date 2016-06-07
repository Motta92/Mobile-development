//
//  MessageManager.swift
//  Proyecto
//
//  Created by Luisa Motta on 6/06/16.
//  Copyright © 2016 Luisa Motta. All rights reserved.
//

import UIKit
import SQLite
public class MessageManager {
    private var db:Connection
    
    private var messages:Table
    private var fromColumn:Expression<Int>
    private var toColumn:Expression<Int>
    private var textColumn:Expression<String>
    private var dateColumn:Expression<String>
    private var idColumn:Expression<Int>
    
    init(){
        let path = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true).first!
        
        db = try! Connection("\(path)/db.sqlite3")
        messages = Table("message")
        idColumn = Expression<Int>("id")
        fromColumn = Expression<Int>("from_M")
        toColumn = Expression<Int>("to_M")
        textColumn = Expression<String>("text")
        dateColumn = Expression<String>("date")
        
        //try! db.run(tasks.drop(ifExists: true))
        try! db.run(messages.create(ifNotExists: true) { t in
            t.column(idColumn, primaryKey: PrimaryKey.Autoincrement)
            t.column(fromColumn)
            t.column(toColumn)
            t.column(textColumn)
            t.column(dateColumn)
            })
    }
    
    func addMessage(m:Messages) {
        try! db.run(messages.insert(fromColumn <- m.from, toColumn <- m.to, textColumn <- m.text, dateColumn<-m.date))
    }
    
    func getMessage(from:Int,to:Int) -> Messages{
        //Select * from messages
        let query = messages.select(textColumn)           // SELECT "email" FROM "users"
            .filter(fromColumn == from && toColumn == to)     // WHERE "name" IS NOT NULL
        
        let men = Array(try! db.prepare(query))[0]
        return Messages(id:men.get(idColumn), from: men.get(fromColumn), to:men.get(toColumn),text: men.get(textColumn), date: men.get(dateColumn));
    }
    
    
    func count() -> Int {
        return db.scalar(messages.count)
    }
}