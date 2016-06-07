//
//  ContactViewController.swift
//  Proyecto
//
//  Created by Luisa Motta on 6/06/16.
//  Copyright © 2016 Luisa Motta. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class ContactViewController: UIViewController{
    var listContacts : [Contact] = [Contact]()
    
    
    @IBOutlet var TableView: UITableView!
    
    override func viewDidLoad() {
        print("la puta madre 25cm")
        Alamofire.request(.GET, "http://192.168.0.20:8090/rest/contacts/1")
            .responseJSON{response in
                guard response.result.error == nil else{
                    
                    return
                }
                print("before value")
                if let value = response.result.value{
                    print("after value")
                    let data = JSON(value)
                    if let items = data.array{
                        for item in items{
                            let c = Contact(id: Int(String(item["userId"]))!,username: String(item["userName"]), name: String(item["nombre"]))
                            self.listContacts.append(c)
                            print("asdasd: " + c.userName)
                        }
                    }
                }
        }
        
        let seconds  = 50.0
        let delay = seconds * Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        dispatch_after(dispatchtime,dispatch_get_main_queue(), {
            self.viewWillAppear(true)
        })
        
        print(listContacts.count)
        super.viewDidLoad()
        
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        //let cell = UITableViewCell(style: UITableViewCellStyle.Subtitle, reuseIdentifier: nil)
        let cell = UITableViewCell()
        cell.textLabel?.text = listContacts[indexPath.row].nombre
        
        return cell
    }
    
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let barViewControllers = segue.destinationViewController as! UITabBarController
        let messageNav = barViewControllers.viewControllers![0] as! FirstViewController
        let fileNav = barViewControllers.viewControllers![1] as! SecondViewController
        
        messageNav.text = "Ayy papa"
        fileNav.text = "Files papa"
    }
    
}