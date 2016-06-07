//
//  Contact.swift
//  Proyecto
//
//  Created by Luisa Motta on 6/06/16.
//  Copyright © 2016 Luisa Motta. All rights reserved.
//

import UIKit

class Contact {
    var userId:Int
    var userName:String
    var nombre:String
    
    init (id:Int, username:String,name:String){
        self.userId = id
        self.nombre = name
        self.userName = username
    }
}
