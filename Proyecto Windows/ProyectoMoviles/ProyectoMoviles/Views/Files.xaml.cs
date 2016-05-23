using Microsoft.Win32;
using ProyectoMoviles.DB;
using ProyectoMoviles.Model;
using RestSharp;
using RestSharp.Extensions;
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
using System.Windows.Shapes;

namespace ProyectoMoviles.Views
{
    /// <summary>
    /// Interaction logic for Files.xaml
    /// </summary>
    public partial class Files : Window
    {
        public int contactId { get; set; }
        public List<SharedFiles> files;

        public Files(int indexOfContact)
        {
            this.contactId = indexOfContact;
            InitializeComponent();
            files = new List<SharedFiles>();

            var client = new RestClient("http://localhost:8090");
            var request = new RestRequest("rest/shared_files/" + contactId + "/3", Method.GET);
            var result = client.Execute<List<SharedFiles>>(request).Data;

            foreach (SharedFiles sf in result)
            {
                files.Add(sf);
                Console.WriteLine("sf.name: " + sf.name);
            }

            FilesListView.ItemsSource = files;
        }

        private void uploadFiles(object sender, RoutedEventArgs e) {

            OpenFileDialog openFileDialog = new OpenFileDialog();
            if (openFileDialog.ShowDialog() == true)
            {
                

                var client = new RestClient("http://localhost:8090");
                var request = new RestRequest("rest/files/" + contactId + "/3", Method.POST);

                request.AddFile("file", openFileDialog.FileName);

                Console.WriteLine("filename: " + openFileDialog.FileName);

                // execute the request
                //calling server with restClient
                var result = client.ExecuteAsync(request, (response) =>
                {

                    if (response.StatusCode == HttpStatusCode.OK)
                    {
                        //upload successfull
                        MessageBox.Show("Upload completed succesfully...\n" + response.Content);
                    }
                    else
                    {
                        //error ocured during upload
                        MessageBox.Show(response.StatusCode + "\n" + response.StatusDescription);
                    }
                });
            }

            else {
                MessageBox.Show("No file selected!");
            }
                

            
        }

        private void downloadFile(object sender, MouseButtonEventArgs e)
        {
            int index = FilesListView.SelectedIndex;

            var client = new RestClient("http://localhost:8090");
            var request = new RestRequest("rest/files/" + files.ElementAt(index).id, Method.GET);

            client.DownloadData(request).SaveAs("C:/Users/Carlos/Desktop/" + files.ElementAt(index).name);
        }
    }
}
