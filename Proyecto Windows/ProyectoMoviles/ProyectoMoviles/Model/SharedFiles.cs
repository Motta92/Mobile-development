using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class SharedFiles
    {
        public int id { get; set; }
        public string name { get; set; }
        public string contentType { get; set; }
        public int from { get; set; }
        public int to { get; set; }
        public string date { get; set; }

        public SharedFiles() { }
        public SharedFiles(int id, string name, string contentType, int from, int to, string date) {
            this.id = id;
            this.name = name;
            this.contentType = contentType;
            this.from = from;
            this.to = to;
            this.date = date;
        }


    }
}
