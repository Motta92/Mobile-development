//
//  GetContacts.swift
//  IOSproj
//
//  Created by Carvajal Empaques on 5/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import Restofire

class ContactGETService: Requestable {
    
    typealias Model = [[String: AnyObject]]
    var path: String = "contacts/"+String(AppConfig.MyID)
    
}