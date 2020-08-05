package com.viacom.datamonks;

public class Sample {
static int start=9, startm=9;
static String ans="9";
	public static void main(String[] args) {


				// TODO Auto-generated method stub
				int cons = 8;
				int num = 9;
				int i,j,k,l,m;
				
				for(i=1;i<=num;i++)
				{
				    for(k=8;k>=i;k--)
				    {
				        System.out.print("  ");
				    }
				    for(j=1;j<=i;j++)
				    {
				        System.out.print(j + " ");
				    }
				    System.out.println(" " + "X" + " " +cons+ " " + "+" + " " +i+ " " + "="+ans);
				    for( l=--start;l>=1;l--)
					{
					  for( m=--startm;m>=l;m--)
			            {
			              ans=ans+" "+m;  
			              // System.out.print(m +" ");
			               
			            }
					 
					  break;
					}
				
				}
			}   
			
			/*public static  String printResult()
			{
				for(int l=start--;l>=1;l--)
				{
				  for(int m=startm--;m>=l;m--)
		            {
		              ans=ans+" "+m;  
		              // System.out.print(m +" ");
		               
		            }
				 
				  break;
				}
				 
				return ans;
			}*/
		
	
}
