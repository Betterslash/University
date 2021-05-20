using Microsoft.AspNetCore.Mvc;

namespace ASP.NETCoreWebApplication.Controllers
{
    public class DocumentController : Controller
    {
        // GET
        public IActionResult Index()
        {
            return View();
        }
    }
}