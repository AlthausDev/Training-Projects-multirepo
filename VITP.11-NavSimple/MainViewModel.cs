using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace VITP._11_NavSimple
{
    public class MainViewModel : ObservableObject
    {
        private PageId _pageId;

        public PageId PageID {
            get { return _pageId;  } 
            set { SetProperty(ref _pageId, value); }
        }

        public MainViewModel()
        {
            PageID = PageId.B;
        }

        private void ChangePage(PageId pageId)
        {
            PageID = pageId;
        }

        public ICommand CMDChangePage => new RelayCommand<PageId>(ChangePage);
    }
}
