using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using static System.Net.Mime.MediaTypeNames;


namespace VITP._09_ImageViewer
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private DirectoryImageList imgList; 
        private string path = Environment.GetFolderPath(Environment.SpecialFolder.MyPictures);

        private void ResetList() { 
            if (IsValidPath(path)) { 
                imgList = new DirectoryImageList(path); 
            } 
            this.DataContext = imgList.Images; 
        }

        private bool IsValidPath(string path) { 
            try { 
                string folder = System.IO.Path.GetFullPath(path); 
                return true; 
            } catch { 
                return false; 
            } 
        }

        public MainWindow()
        {
            InitializeComponent();
            ResetList();     
        }

        private void setPath()
        {
            FolderBrowserDialog folderBrowserDialog = new();
            folderBrowserDialog.ShowDialog();
            path = folderBrowserDialog.SelectedPath;
            ResetList();
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {

        }

        private void ListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            image.Source = (BitmapSource)(sender as System.Windows.Controls.ListBox).SelectedItem;
        }

        private void BtnRotate_Click(object sender, RoutedEventArgs e)
        {
            CachedBitmap cache = new CachedBitmap((BitmapSource)image.Source, 
                BitmapCreateOptions.None, BitmapCacheOption.OnLoad); 
            image.Source = new TransformedBitmap(cache, new RotateTransform(90));
        }

        private void BtnFlip_Click(object sender, RoutedEventArgs e)
        {
            CachedBitmap cache = new((BitmapSource)image.Source, 
                BitmapCreateOptions.None, BitmapCacheOption.OnLoad); 

            ScaleTransform scale = new(-1, -1, image.Source.Width / 2, 
                image.Source.Height / 2); 

            image.Source = new TransformedBitmap(cache, scale);
        }

        private void BtnBN_Click(object sender, RoutedEventArgs e)
        {
            BitmapSource img = (BitmapSource)image.Source; 
            image.Source = new FormatConvertedBitmap(img, PixelFormats.Gray16, BitmapPalettes.Gray256, 1.0);
        }

        private void BtnBlur_Click(object sender, RoutedEventArgs e)
        {
            if (image.Effect != null) { 
                image.Effect = null;     
            }else {         
                image.Effect = new BlurEffect();     
            } 
        }
    }
}