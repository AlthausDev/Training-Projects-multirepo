using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using TODO_V2.Client;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
//builder.RootComponents.Add<HeadOutlet>("head::after");
builder.Services.AddBlazorBootstrap();
builder.Services.AddScoped(sp => new HttpClient { BaseAddress = new Uri(builder.HostEnvironment.BaseAddress) });

builder.Services.AddBlazoredLocalStorage();

await builder.Build().RunAsync();
