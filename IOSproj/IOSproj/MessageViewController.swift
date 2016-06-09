//
//  SecondViewController.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//
// Messages view
import UIKit
import Alamofire
import SwiftyJSON
class MessageViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var MessageList: UITableView!
    var to : Int = 0
    var MessagesGetDB : [Messages] = [Messages]()
    private var MessageDB = MessageManager()
    @IBOutlet weak var MessageText: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.MessagesGetDB = MessageDB.getMessage(AppConfig.MyID, to: self.to)
        
        super.viewDidLoad()
        let seconds  = 1.0
        let delay = seconds * Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        
        dispatch_after(dispatchtime,dispatch_get_main_queue(), {
            self.viewWillAppear(true)
            print("Database lenght " + String(self.MessagesGetDB.count))
        })
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func sendMessage(sender: AnyObject) {
        let text = MessageText.text
        print("FROM ID:" + String(AppConfig.MyID))
        print("TO ID:" + String(self.to))
        Services.PostMessages(AppConfig.MyID, to: self.to, text: text!)
        MessageText.text = ""
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return MessagesGetDB.count
    }
    
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let cell = UITableViewCell(style: UITableViewCellStyle.Subtitle, reuseIdentifier: nil)
        //let cell = UITableViewCell()
        cell.textLabel!.text = self.MessagesGetDB[indexPath.row].text!
        return cell
    }
    override func viewWillAppear(animated: Bool) {
        MessageList.reloadData()
    }

}

