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

class SecondViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate, UITableViewDataSource, UITableViewDelegate {
    var text : String = ""
    var files = [File]()
    let cellIdentifier = "fileCustomCell"
    @IBOutlet weak var tableView: UITableView!
    let imagePicker = UIImagePickerController()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        tableView.delegate = self
        imagePicker.delegate = self
        
        // Do any additional setup after loading the view, typically from a nib.
        
        Alamofire.request(.GET, "http://192.168.0.20:8090/rest/shared_files/1/3")
            .validate()
            .responseJSON { response in
                //debugPrint(response)
                
                if let value = response.result.value{
                    let shared_files = JSON(value)
                    
                    if let file = shared_files.array{
                        for f in file{
                            let newFile = File(id:Int(String(f["id"]))!,name:String(f["name"]),ct:String(f["contentType"]),from:Int(String(f["from"]))!,to:Int(String(f["to"]))!,date:String(f["date"]))
                            
                            self.files.append(newFile)
                            print("Salio?")
                        }
                    }
                }
        }
        
        print("Salio2?")
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier(cellIdentifier) as! fileCustomViewCell
        
        let file = files[indexPath.row]
        //print("files size: " + String(files.count))
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
    
    @IBAction func uploadImage(sender: UIButton) {
        //Alamofire.upload(.POST, "http://192.168.0.20:8090/rest/files/1/3", data: <#T##NSData#>)
        imagePicker.allowsEditing = false
        imagePicker.sourceType = .PhotoLibrary
        
        presentViewController(imagePicker, animated: true, completion: nil)
    }
    
    func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : AnyObject]) {
        if let pickedImage = info[UIImagePickerControllerOriginalImage] as? UIImage{
            
            let imageData = UIImagePNGRepresentation(pickedImage)
            
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
            
            // CREATE AND SEND REQUEST ----------
            
            let urlRequest = urlRequestWithComponents("http://192.168.0.20:8090/rest/files/1/3", imageData: imageData!, filename: "file.png")
            
            Alamofire.upload(urlRequest.0, data: urlRequest.1)
                .progress { (bytesWritten, totalBytesWritten, totalBytesExpectedToWrite) in
                    //print("\(totalBytesWritten) / \(totalBytesExpectedToWrite)")
                }
                .responseJSON { response in
                    print("RESPONSE \(response)")
            }

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
            let selectedFile = files[indexPath.row]
            imageViewController.file = selectedFile
        }
    }
    
    
    
    // this function creates the required URLRequestConvertible and NSData we need to use Alamofire.upload
    func urlRequestWithComponents(urlString:String, imageData:NSData, filename:String) -> (URLRequestConvertible, NSData) {
        
        // create url request to send
        let mutableURLRequest = NSMutableURLRequest(URL: NSURL(string: urlString)!)
        mutableURLRequest.HTTPMethod = Alamofire.Method.POST.rawValue
        let boundaryConstant = "myRandomBoundary12345";
        let contentType = "multipart/form-data;boundary="+boundaryConstant
        mutableURLRequest.setValue(contentType, forHTTPHeaderField: "Content-Type")
        
        
        // create upload data to send
        let uploadData = NSMutableData()
        
        // add image
        uploadData.appendData("\r\n--\(boundaryConstant)\r\n".dataUsingEncoding(NSUTF8StringEncoding)!)
        uploadData.appendData("Content-Disposition: form-data; name=\"file\"; filename=\"\(filename)\"\r\n".dataUsingEncoding(NSUTF8StringEncoding)!)
        uploadData.appendData("Content-Type: image/png\r\n\r\n".dataUsingEncoding(NSUTF8StringEncoding)!)
        uploadData.appendData(imageData)
        
        
        uploadData.appendData("\r\n--\(boundaryConstant)--\r\n".dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        
        // return URLRequestConvertible and NSData
        return (Alamofire.ParameterEncoding.URL.encode(mutableURLRequest, parameters: nil).0, uploadData)
    }
}

