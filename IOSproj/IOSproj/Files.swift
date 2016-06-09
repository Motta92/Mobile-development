//
//  Files.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class File{
    var id:Int
    var name:String
    var contentType:String
    var from:Int
    var to:Int
    var date:String
    
    init(id:Int,name:String,ct:String,from:Int,to:Int,date:String){
        self.id = id
        self.name = name
        self.contentType = ct
        self.from = from
        self.to = to
        self.date = date
    }
}