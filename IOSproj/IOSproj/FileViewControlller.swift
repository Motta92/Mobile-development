//
//  FileViewControlller.swift
//  IOSproj
//
//  Created by Luisa Motta on 7/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

//
//  SecondViewController.swift
//  Proyecto
//
//  Created by Luisa Motta on 5/06/16.
//  Copyright © 2016 Luisa Motta. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class FileViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate, UITableViewDataSource, UITableViewDelegate {
    var text : String = ""
    var files = [File]()
    let cellIdentifier = "fileCustomCell"
    var to : Int = 0
    
    let imagePicker = UIImagePickerController()
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        imagePicker.delegate = self
        
        self.files = Services.GetSharedFiles(self.to)
        
        // Do any additional setup after loading the view, typically from a nib.
        let seconds  = 1.0
        let delay = seconds * Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        dispatch_after(dispatchtime,dispatch_get_main_queue(), {
            self.viewWillAppear(true)
        })
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier) as! fileCustomViewCell
        
        let file = files[indexPath.row]
        print("files size: " + String(files.count))
        cell.filename.text = file.name
        cell.uploadDate.text = file.date
        cell.imageThumbnail.image = UIImage(named: "template.png")
        
        return cell
    }
    
    // numero de rows a mostrar en el tableview
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return files.count
    }
    
    override func viewWillAppear(animated: Bool) {
        tableView.reloadData()
    }
    
    
    @IBAction func upload_Image(sender: UIButton) {
        //Alamofire.upload(.POST, "http://192.168.0.20:8090/rest/files/1/3", data: <#T##NSData#>)
        imagePicker.allowsEditing = false
        imagePicker.sourceType = .PhotoLibrary
        
        presentViewController(imagePicker, animated: true, completion: nil)
    }
    
    func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : AnyObject]) {
        if let pickedImage = info[UIImagePickerControllerOriginalImage] as? UIImage{
            
            let imageData = UIImagePNGRepresentation(pickedImage)
            
            Services.PostFile(imageData!, filename: "file.png",to:self.to)
            
            /*
             print(pickedImage.pathComponents![1])
             print(pickedImage.pathExtension)
             
             let fileURL = NSBundle.mainBundle().URLForResource(pickedImage.pathComponents![1], withExtension: pickedImage.pathExtension)
             Alamofire.upload(.POST, "http://192.168.0.20:8090/rest/files/1/3", file: fileURL!).progress { bytesWritten, totalBytesWritten, totalBytesExpectedToWrite in
             print(totalBytesWritten)
             
             // This closure is NOT called on the main queue for performance
             // reasons. To update your ui, dispatch to the main queue.
             dispatch_async(dispatch_get_main_queue()) {
             print("Total bytes written on main queue: \(totalBytesWritten)")
             }
             }
             .validate()
             .responseJSON { response in
             debugPrint(response)
             }
             */
        }
        
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    func imagePickerControllerDidCancel(picker: UIImagePickerController) {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let imageViewController = segue.destinationViewController as! DownloadFileController
        if let selectedFileCell = sender as? fileCustomViewCell {
            let indexPath = tableView.indexPathForCell(selectedFileCell)!
            var selfile = files[(tableView.indexPathForSelectedRow?.row)!]
            let selectedFile = files[indexPath.row]
            imageViewController.file = selectedFile
            imageViewController.id = selfile.id
            
        }
    }
    
}


