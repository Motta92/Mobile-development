//
//  Messages.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class Messages {
    var id:Int?
    var from:Int?
    var to:Int?
    var text:String?
    
    init(id:Int!,from:Int!, to:Int!, text:String!){
        self.id = id
        self.from = from
        self.to = to
        self.text = text
    }
}