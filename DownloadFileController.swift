//
//  DownloadFileController.swift
//  Proyecto
//
//  Created by Luisa Motta on 6/06/16.
//  Copyright © 2016 Luisa Motta. All rights reserved.
//

import UIKit
import Alamofire

class DownloadFileController: UIViewController {
    
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
            
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func downloadFile(sender: UIButton) {
        /*
        let destination = Alamofire.Request.suggestedDownloadDestination(directory: .DocumentDirectory, domain: .UserDomainMask)
        Alamofire.download(.GET, "http://192.168.0.20:8090/rest/files/2", destination: destination)
        */
        
        let destination = Alamofire.Request.suggestedDownloadDestination(directory: .DocumentDirectory, domain: .UserDomainMask)
        
        Alamofire.download(.GET, "http://192.168.0.20:8090/rest/files/2", destination: destination)
            .progress { bytesRead, totalBytesRead, totalBytesExpectedToRead in
                print(totalBytesRead)
                
                // This closure is NOT called on the main queue for performance
                // reasons. To update your ui, dispatch to the main queue.
                dispatch_async(dispatch_get_main_queue()) {
                    print("Total bytes read on main queue: \(totalBytesRead)")
                }
            }
            .response { _, _, _, error in
                if let error = error {
                    print("Failed with error: \(error)")
                } else {
                    print("Downloaded file successfully")
                }
        }
        
        
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