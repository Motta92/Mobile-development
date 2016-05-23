using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProyectoMoviles.Model
{
    public class Message
    {
        public int to { get; set; }
        public int from { get; set; }
        public String text { get; set; }
        public string date {get; set;}

        public Message() { }
        public Message(int to, int from, string message, string date) {
            this.to = to;
            this.from = from;
            this.text = message;
            this.date = date;
        }
    }
}
