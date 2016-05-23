using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class Contact
    {
        public int UserId { get; set; }
        public String nombre { get; set; }
        public String userName { get; set; }

        public Contact(int userId, String UserName, String Nombre)
        {
            this.nombre = Nombre;
            this.userName = UserName;
        }
        public Contact() { }
    }
}
