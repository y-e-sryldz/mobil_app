using Bogus;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Data;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly AppDbContext _context;

        public UserController(AppDbContext context)
        {
            _context = context;
        }
        [HttpPost]
        [Route("calculateBmi")]
        public async Task<IActionResult> CalculateBmi([FromBody] User user)
        {
            if (user.Height <= 0 || user.Weight <= 0)
            {
                return BadRequest(new { error = "Height and weight must be greater than 0." });
            }

            // BMI Hesaplama: BMI = Weight (kg) / (Height (m) ^ 2)
            user.Bmi = user.Weight / Math.Pow(user.Height / 100, 2);

            // Güncel kullanıcının kaydedilmesi
            _context.Users.Add(user);
            await _context.SaveChangesAsync();

            return Ok(new
            {
                Message = "User BMI calculated and saved successfully.",
                User = user
            });
        }
        [HttpPost("fake/{count}")]
        public async Task<IActionResult> GenerateFakeUsers(int count)
        {
            if (count <= 0)
                return BadRequest("Count must be greater than 0.");
            var faker = new Faker<User>()
                .RuleFor(u => u.Id, f => Guid.NewGuid())
                .RuleFor(u => u.Name, f => f.Person.FullName)
                .RuleFor(u => u.Email, f => f.Internet.Email());
            var fakeUsers = faker.Generate(count);
            await _context.Users.AddRangeAsync(fakeUsers);
            await _context.SaveChangesAsync();

            return Ok(new
            {
                Message = $"{count} fake users created successfully!",
                Users = fakeUsers
            });
        }
        [HttpGet]
        [Route("getAllUsersBogus")]
        public IActionResult GetAllUsersBogus()
        {
            return Ok(FakeDatabase.Users);
        }
        // GET: api/users
        [HttpGet]
        [Route("getAllUsers")]
        public async Task<IActionResult> GetAllUsers()
        {
            var users = await _context.Users.ToListAsync();
            return Ok(users);
        }

        // GET: api/users/{id}
        [HttpGet("{id}")]
        public async Task<IActionResult> GetUserById(Guid id)
        {
            var user = await _context.Users.FindAsync(id);
            if (user == null)
                return NotFound("User not found");
            return Ok(user);
        }

        // POST: api/users
        [HttpPost]
        [Route("createUser")]
        public async Task<IActionResult> CreateUser([FromBody] User newUser)
        {
            newUser.Id = Guid.NewGuid();
            _context.Users.Add(newUser);
            await _context.SaveChangesAsync();
            return CreatedAtAction(nameof(GetUserById), new { id = newUser.Id }, newUser);
        }

        // PUT: api/users/{id}
        [HttpPut]
        [Route("updateUser/{id}")]
        public async Task<IActionResult> UpdateUser(Guid id, [FromBody] User updatedUser)
        {
            var user = await _context.Users.FindAsync(id);
            if (user == null)
                return NotFound("User not found");

            user.Name = updatedUser.Name;
            user.Email = updatedUser.Email;

            await _context.SaveChangesAsync();
            return NoContent();
        }

        // DELETE: api/users/{id}
        [HttpDelete]
        [Route("deleteUser/{id}")]
        public async Task<IActionResult> DeleteUser(Guid id)
        {
            var user = await _context.Users.FindAsync(id);
            if (user == null)
                return NotFound("User not found");

            _context.Users.Remove(user);
            await _context.SaveChangesAsync();
            return NoContent();
        }
    }
}
