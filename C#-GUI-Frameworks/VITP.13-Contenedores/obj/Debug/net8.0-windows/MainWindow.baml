   M S B A M L   `   `   `  ����E  AExamen5AAS, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:Examen5AAS
Examen5AAS  5   	       Examen5AAS.MainWindow   5   	   W SPresentationCore, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=8.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=8.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:Examen5AAS     5   	     � Title$  
MainWindow��5   	   $	��450��6   $	��800��6)   .��5      � .+�5   
   �� -   5        $
��Left=�5      $	��Top=�6*   $��10q�6B   $	��200��5      $��30��6   ��5      �� *ContenidoFilterText+5   '   �� 5   
   -   5         ��Name$ 
FiltrarBtn��5      $��Filtrar por Contenido��6%   $
��Left=�5      $	��Top=�6)   $��
220,10,0,0q�6A   $	��150��5      $��30��6   ${�False��6&   ��5      �� *FilterByContenidoCommand+5   &   &   System.Windows.Controls.DataGrid  5   
      AutoGenerateColumns$ False��68   $��Stretch=�5      $��Stretch=�6.   $��10,50,10,10q�6J   ��5      �� *Contenedores+   Columns 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   Header$ 
Referencia��6%      Width5  /System.Windows.Controls.DataGridLengthConverter$
 Auto 6X   1  +System.Windows.Controls.DataGridBoundColumn   Binding 69   �� *
Referencia+6X     5      $
 Peso��6%   $
 Auto 6L    63   �� *Peso+6L     5      $ Volumen��6%   $
 Auto 6R    66   �� *	Volumen+6R     5      $ Tipo de Contenido��6%   $
 Auto 6b    6@   �� *TipoContenido+6b   5!      �� 5"   
   $��Peso medio: ��6   $
��Left=�6'   $��
488,12,0,0q�6B   $	��Top=�6V   $	��166��6n   �� 5#   
   $
��Left=�6?   $��
571,11,0,0q�6Z   $	��Top=�6n   $��35��6�   $��28��6�   ��6   ��  �� Path$ ContenedoresPesoMedio�6�   5$      5%      