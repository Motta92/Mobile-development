//
//  ContactViewController.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
import RestEssentials
import Restofire

class FirstViewController: UIViewController{
    var listContacts : [Contact] = [Contact]()
    var person: [[String: AnyObject]]!
    var requestOp: RequestOperation<ContactGETService>!
    func getPerson() {
        requestOp = ContactGETService().executeTask() {
            if let value = $0.result.value {
                for item in value{
                    let c = Contact(id: Int(String(item["userId"]!)),username: String(item["userName"]!), name: String(item["nombre"]!))
                    self.listContacts.append(c)
                    print("Nombre C : " + String(item["nombre"]!))
                }
            }
        }
    }
    
    deinit {
        requestOp.cancel()
    }
    override func viewDidLoad() {
        self.getPerson()
        super.viewDidLoad()
        let seconds  = 1.0
        let delay = seconds * Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        
        dispatch_after(dispatchtime,dispatch_get_main_queue(), {
            self.viewWillAppear(true)
        })
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let destination = segue.destinationViewController as! ContactViewController
        destination.Contacts = self.listContacts
    }
}

