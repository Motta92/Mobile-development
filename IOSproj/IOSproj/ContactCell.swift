//
//  ContactCell.swift
//  IOSproj
//
//  Created by Carvajal Empaques on 5/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class ContactCell : UITableViewCell{
    
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var userNameLabel: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}
