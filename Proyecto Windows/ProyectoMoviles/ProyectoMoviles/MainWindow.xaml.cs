using ProyectoMoviles.DB;
using ProyectoMoviles.Model;
using ProyectoMoviles.Views;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Xml;
using System.Xml.Linq;

namespace ProyectoMoviles
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        List<Contact> Contacts = new List<Contact>();

        public MainWindow()
        {
            InitializeComponent();
            DBmanager.createDataBase();

            var client = new RestClient("http://localhost:8090");

            var request = new RestRequest("rest/contacts/3", Method.GET);

            var result = client.Execute<List<Contact>>(request).Data;

            foreach (Contact c in result) {
                Contacts.Add(c);
            }
            
            ContactListView.ItemsSource = Contacts;
        }

        private void toMessages(object sender, RoutedEventArgs e)
        {
            var item = (sender as FrameworkElement).DataContext;
            int index = ContactListView.Items.IndexOf(item);

            int contactIndex = Contacts.ElementAt(index).UserId;

            Console.WriteLine("Index of item clicked messages: " + contactIndex);

            Messages newMessageWpf = new Messages(contactIndex);
            newMessageWpf.Show();
        }

        private void toFiles(object sender, RoutedEventArgs e)
        {
            var item = (sender as FrameworkElement).DataContext;
            int index = ContactListView.Items.IndexOf(item);

            int contactIndex = Contacts.ElementAt(index).UserId;

            Console.WriteLine("Index of item clicked files: " + contactIndex);

            Files newFilesWpf = new Files(contactIndex);
            newFilesWpf.Show();
        }
    }
}
