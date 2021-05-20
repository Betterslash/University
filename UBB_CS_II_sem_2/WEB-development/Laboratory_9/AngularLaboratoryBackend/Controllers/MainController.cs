using Microsoft.AspNetCore.Mvc;

namespace AngularLaboratoryBackend.Controllers
{
    public class MainController : Controller
    {
        // GET
        public IActionResult Index()
        {
            return View();
        }
    }
}