using System.IO;
using System.Windows.Media.Imaging;

namespace VITP._09_ImageViewer
{
    public class DirectoryImageList
    {
        private string path; 

        public DirectoryImageList(string path) { 
            this.path = path;
            Images = new List<BitmapSource>();
            LoadImages(); 
        }

        public List<BitmapSource> Images
        {
            get; set;
        }

        public string Path { 
            get { 
                return path; 
            } 
            set { 
                path = value; 
                LoadImages(); 
            } 
        }

        private void LoadImages()
        {
            Images.Clear();
            BitmapImage img;
            Array.ForEach(Directory.GetFiles(path), f =>{
                try {
                    img = new BitmapImage(new Uri(f));
                    Images.Add(img);
                }
                catch { // Se deja vacío; ignorando cualquier fichero que no se pueda cargar como imagen                 }             });         }     } 
                }
            });
        }
    }
}
