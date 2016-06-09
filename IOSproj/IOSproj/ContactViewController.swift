//
//  FirstViewController.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//
//Contact view

import UIKit
import Restofire
class ContactViewController: UIViewController, UITableViewDelegate,UITableViewDataSource {
    var Contacts : [Contact] = [Contact]()
    var listMessages : [Messages] = [Messages]()
    var to : Int = 0
    var requestOp: RequestOperation<MessageGETService>!
    private var MessageDB = MessageManager()
    
    func getMessage(from: Int, to: Int) {
        let Request = MessageGETService()
        Request.path = Request.path+String(from) + "/"+String(to)
        requestOp = Request.executeTask() {
            if let value = $0.result.value {
                for item in value{
                    let m = Messages(id:0,from: Int(String(item["from"]!)),to: Int(String(item["to"]!)), text: String(item["text"]!))
                    self.MessageDB.addMessage(m)
                    print("Nombre M: " + String(item["text"]!))
                }
            }
        }
    }
    
    @IBOutlet weak var contacList: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        contacList.dataSource = self
        contacList.delegate = self
        print(Contacts.count)
        contacList.reloadData()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Contacts.count
    }
    
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        //let cell = UITableViewCell(style: UITableViewCellStyle.Subtitle, reuseIdentifier: nil)
        //let cell = UITableViewCell()
        let cell:ContactCell = contacList.dequeueReusableCellWithIdentifier("ContactCell") as! ContactCell
        cell.userNameLabel!.text = Contacts[indexPath.row].userName!
        cell.nameLabel!.text = Contacts[indexPath.row].nombre!
        return cell
    }
    override func viewWillAppear(animated: Bool) {
        contacList.reloadData()
    }
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let receiver = Contacts[(contacList.indexPathForSelectedRow?.row)!]
        let barViewControllers = segue.destinationViewController as! UITabBarController
        let messageDestination = barViewControllers.viewControllers![0] as! MessageViewController
        let fileDestination = barViewControllers.viewControllers![1] as! FileViewController
        messageDestination.to = receiver.userId!
        fileDestination.to = receiver.userId!

    }

}

