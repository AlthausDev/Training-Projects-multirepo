   M S B A M L   `   `   `  ����M  IVITP.12-BeerLandia, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullK*clr-namespace:VITP._12_Ommnisiah.ViewModelVITP._12_Ommnisiah.ViewModel  5      $    VITP._12_Ommnisiah.Pages.Page1   5      W SPresentationCore, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      <local*clr-namespace:VITP._12_Ommnisiah.ViewModel     5        N� Title$  Page1��5	       � DataContext 5      4   .VITP._12_Ommnisiah.ViewModel.BeerListViewModel  5   
    � 	Resources 5      *  $System.Windows.Controls.DataGridCell �� 
TargetType �� Property �� Value=  7System.Windows.Controls.Primitives.DataGridColumnHeader)  #System.Windows.Controls.DataGridRow��%N  '        '  �     '  t    �� 5   
   "  6   .�5      ��  � ��$ 5q�6(   �� 5       � ��$ 1q�60   �� 5       � ��
 �����6,   �� 5       � ��
 �   �6+   5      �� 5   
   "  6   .�5      ��  � ��
 �����6+   �� 5       � ��
 �   �6+   �� 5       � ��$
 Bold"�6+   �� 5       � ��$ Center=�6;   5      �� 5!   
   "  6   .�5"      ��  � ��
 �����6+   �� 5#       � ��
 �   �6+   �� 5$       � ��$ Normal"�6+   �� 5%       � ��$ 12(�6)   5&      5'      .��5)      � .+�5*   
   &   System.Windows.Controls.DataGrid     AutoGenerateColumns$ False��61   $
��Auto��6M   $
��Auto��6Z   ��6   �� *Beers+   Columns 5+      0  *System.Windows.Controls.DataGridTextColumn  5,      ,  &System.Windows.Controls.DataGridColumn   Header$ ID��6%   	   Width5  /System.Windows.Controls.DataGridLengthConverter$
	 Auto 6H   1	  +System.Windows.Controls.DataGridBoundColumn
 	  Binding
 61   �� *Id+6H     5-      $	 ABV��6%   $
	 Auto 6J   
 62   �� *Abv+6J     5.      $ Nombre��6%   $
	 Auto 6N   
 65   �� *Name+6N     5/      $ Estilo��6%   $
	 Auto 6O   
 65   �� *Style+6O     50      $ 	Cervecera��6%   $
	 Auto 6S   
 68   �� *Brewer+6S     51      $ Ciudad��6%   $
	 Auto 6N   
 65   �� *City+6N     52      $ Estado��6%   $
	 Auto 6O   
 65   �� *State+6O     53      $ Etiqueta��6%   $
	 Auto 6Q   
 67   �� *Label+6Q   55      56      57      