//
//  Services.swift
//  IOSproj
//
//  Created by Luisa Motta on 8/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
class Services{
    static func PostMessages(from :Int, to:Int,text:String){
        let message : Dictionary<String,AnyObject> = Dictionary<String,AnyObject>(dictionaryLiteral: ("to",to),("from",AppConfig.MyID),("text",text))
        
        Alamofire.request(.POST,AppConfig.URL+"messages/",parameters:message, encoding: .JSON)
    }
    
    static func GetSharedFiles(to:Int)->[File]{
        var returnFiles:[File] =  [File]()
        var tmpFiles:[File] =  [File]()
        Alamofire.request(.GET, AppConfig.URL+"shared_files/"+String(AppConfig.MyID)+"/"+String(to))
            .validate()
            .responseJSON { response in
                //debugPrint(response)
                
                if let value = response.result.value{
                    let shared_files = JSON(value)
                    
                    if let file = shared_files.array{
                        for f in file{
                            let newFile = File(id:Int(String(f["id"]))!,name:String(f["name"]),ct:String(f["contentType"]),from:Int(String(f["from"]))!,to:Int(String(f["to"]))!,date:String(f["date"]))
                            
                            tmpFiles.append(newFile)
                            print("Salio?")
                        }
                    }
                }
            }
        
        let seconds  = 1.0
        let delay = seconds * Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        dispatch_after(dispatchtime,dispatch_get_main_queue(), {
            returnFiles = tmpFiles
        })
        return returnFiles
    }
    
    static func PostFile(imageData:NSData, filename:String, to:Int){
        
        // create url request to send
        let mutableURLRequest = NSMutableURLRequest(URL: NSURL(string: AppConfig.URL+"files/"+String(AppConfig.MyID)+"/"+String(to))!)
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
        let urlRequest = (Alamofire.ParameterEncoding.URL.encode(mutableURLRequest, parameters: nil).0, uploadData)
        
        
        
        Alamofire.upload(urlRequest.0, data: urlRequest.1)
            .progress { (bytesWritten, totalBytesWritten, totalBytesExpectedToWrite) in
                print("\(totalBytesWritten) / \(totalBytesExpectedToWrite)")
            }
            .responseJSON { response in
                print("RESPONSE \(response)")
        }
    }
    
    static func GetFile(idF: Int){
        let destination = Alamofire.Request.suggestedDownloadDestination(directory: .DocumentDirectory, domain: .UserDomainMask)
        
        Alamofire.download(.GET, AppConfig.URL+"files/"+String(idF), destination: destination)
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
    }
}
