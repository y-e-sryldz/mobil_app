using Bogus;
using WebApi.Models;

namespace WebApi.Data;

public static class FakeDatabase
{
    public static List<User> Users { get; set; }

    static FakeDatabase()
    {
        var faker = new Faker<User>()
            .RuleFor(u => u.Id, f => Guid.NewGuid())
            .RuleFor(u => u.Name, f => f.Person.FullName)
            .RuleFor(u => u.Email, f => f.Internet.Email());

        Users = faker.Generate(10);
    }
}