//
//  MessageManager.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import SQLite
public class MessageManager {
    private var db:Connection
    
    private var messages:Table
    private var fromCol:Expression<Int>
    private var toCol:Expression<Int>
    private var textCol:Expression<String>
    private var idCol:Expression<Int>
    
    init(){
        let path = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true).first!
        
        db = try! Connection("\(path)/db.sqlite3")
        messages = Table("Mensajes")
        idCol = Expression<Int>("id")
        fromCol = Expression<Int>("de")
        toCol = Expression<Int>("para")
        textCol = Expression<String>("mensaje")
        
        //try! db.run(tasks.drop(ifExists: true))
        try! db.run(messages.create(ifNotExists: true) { t in
            t.column(idCol, primaryKey: PrimaryKey.Autoincrement)
            t.column(fromCol)
            t.column(toCol)
            t.column(textCol)
            })
    }
    
    func addMessage(m:Messages) {
        print(self.count())
        try! db.run(messages.insert(fromCol <- m.from!, toCol <- m.to!, textCol <- m.text!))
    }
    
    func getMessage(from:Int,to:Int) -> [Messages]{
        //Select * from messages
        let query = messages.select(textCol).filter(fromCol == from && toCol == to)
        let men = Array(try! db.prepare(query))
        var mreturn : [Messages] = [Messages]()
        for m in men{
            mreturn.append(Messages(id:m.get(idCol), from: m.get(fromCol), to:m.get(toCol),text: m.get(textCol)))
            print("Mensaje: " + m.get(textCol))
        }
        return mreturn
        //return Messages(from: men.get(fromColumn), to:men.get(toColumn),text: men.get(textColumn));
    }
    
    
    func count() -> Int {
        return db.scalar(messages.count)
    }
}