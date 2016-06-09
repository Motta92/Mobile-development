//
//  DownloadFileController.swift
//  IOSproj
//
//  Created by Luisa Motta on 7/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import Alamofire

class DownloadFileController: UIViewController {
    @IBOutlet weak var myImageVIew: UIImageView!
    var id : Int?
    var file: File?
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        if let file = file {
            self.file!.id = file.id
            self.file!.name = file.name
            self.file!.contentType = file.contentType
            self.file!.from = file.from
            self.file!.to = file.to
            self.file!.date = file.date
            
            //let path: String? = NSBundle.mainBundle().pathForResource("file", ofType: "png", inDirectory: "Documents")
            //debugPrint(path)
            //let imageFromPath = UIImage(contentsOfFile: path!)!
            //self.myImageVIew.image = imageFromPath
            
            let paths = NSSearchPathForDirectoriesInDomains(NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask, true)
            let documentsDirectory: AnyObject = paths[0]
            let dataPath = documentsDirectory.stringByAppendingPathComponent("/file.png")
            
            if NSFileManager.defaultManager().fileExistsAtPath(dataPath){
                
                let imageis: UIImage = UIImage(contentsOfFile: dataPath)!
                self.myImageVIew.image = imageis
                
                let data: NSData = UIImagePNGRepresentation(imageis)!
                
            }
            
            
            
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func downloadFile(sender: UIButton) {
        Services.GetFile(self.id!)
        
        /*
         let destination = Alamofire.Request.suggestedDownloadDestination(directory: .DocumentDirectory, domain: .UserDomainMask)
         Alamofire.download(.GET, "http://192.168.0.20:8090/rest/files/2", destination: destination)
         */
        
        /*
         Alamofire.download(.GET, "http://192.168.0.20:8090/rest/files/2") { temporaryURL, response in
         let fileManager = NSFileManager.defaultManager()
         let directoryURL = fileManager.URLsForDirectory(.DocumentDirectory, inDomains: .UserDomainMask)[0]
         let pathComponent = response.suggestedFilename
         
         return directoryURL.URLByAppendingPathComponent(pathComponent!)
         }
         */
    }
    
    
}