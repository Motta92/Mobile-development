using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class Contact
    {
        public String Name { get; set; }
        public String Username { get; set; }

        public Contact(String Name, String Username)
        {
            this.Name = Name;
            this.Username = Username;
        }
    }
}
