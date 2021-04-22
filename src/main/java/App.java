
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

import models.Hero;
import models.Squad;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        ProcessBuilder process = new ProcessBuilder();
        int port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int totalHeroes = Hero.getAllHeroes().size();
            int totalSquads = Squad.getAllSquads().size();
            int squadlessHeroes = 0;
            int squadfullHeroes = 0;
            for (Hero hero : Hero.getAllHeroes()) {
                if (hero.getSquadList().equals("")) {
                    squadlessHeroes += 1;
                } else {
                    squadfullHeroes += 1;
                }
            }
            model.put("totalHeroes", totalHeroes);
            model.put("totalSquads", totalSquads);
            model.put("squadlessHeroes", squadlessHeroes);
            model.put("squadfullHeroes", squadfullHeroes);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String uniqueId = request.queryParams("uniqueId");
            request.session().attribute("uniqueId", uniqueId);
            model.put("uniqueId", uniqueId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get hero details route
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String specialPower = request.queryParams("powers");
            String weakness = request.queryParams("weakness");
            String gender = request.queryParams("gender");

            Hero newHero = new Hero(name, age, specialPower, weakness,gender);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> squadlessHeroes = new ArrayList<Hero>();
            for (Hero hero : Hero.getAllHeroes()) {
                if (hero.getSquadList().equals("")) {
                    squadlessHeroes.add(hero);
                }
            }
            model.put("squadlessHeroes", squadlessHeroes);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> squadlessHeroes = new ArrayList<Hero>();
            for (Hero hero : Hero.getAllHeroes()) {
                if (hero.getSquadList().equals("")) {
                    squadlessHeroes.add(hero);
                }
            }

            String name = request.queryParams("name");
            String cause = request.queryParams("cause");
            String heroName = request.queryParams("founder");
            Hero squadFounder = null;
            for (Hero hero : squadlessHeroes) {
                if (hero.getName().equalsIgnoreCase(heroName)) {
                    squadFounder = hero;
                    break;
                }
            }
            assert squadFounder != null;
            Squad newSquad = new Squad(name, cause, squadFounder);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("allHeroes", Hero.getAllHeroes());
            model.put("allSquads", Squad.getAllSquads());
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //get: each hero detail page
        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero foundHero = Hero.searchHero(itemId);
            model.put("hero", foundHero);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroDetails.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero updateHero = Hero.searchHero(itemId);
            updateHero.updateName(request.queryParams("name"));
            updateHero.updateAge(Integer.parseInt(request.queryParams("age")));
            updateHero.updateSpecialPower(request.queryParams("power"));
            updateHero.updateWeakness(request.queryParams("weakness"));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: update hero details
        get("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero updateHero = Hero.searchHero(itemId);
            model.put("updateHero", updateHero);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: remove single hero
        get("/heroes/:id/remove", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero.removeHero(itemId);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        //Post: Update squad members by recruiting
        post("/squads/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            String heroName = request.queryParams("addHero");
            Hero heroToAdd = null;
            for (Hero hero : Hero.getAllHeroes()) {
                if (hero.getName().equalsIgnoreCase(heroName)) {
                    heroToAdd = hero;
                    break;
                }
            }
            foundSquad.changeHeroSquad(heroToAdd, foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: Update squad
        get("/squads/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            List<Hero> nonMembers = new ArrayList<>();
            for (Hero hero : Hero.getAllHeroes()) {
                if (!hero.getSquadList().equalsIgnoreCase(foundSquad.getName())) {
                    nonMembers.add(hero);
                }
            }
            model.put("nonMembers", nonMembers);
            model.put("squad", foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "update.hbs");
        }, new HandlebarsTemplateEngine());

        post("/squads/:id/remove", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            String heroName = request.queryParams("removeHero");
            Hero heroToRemove = null;
            for (Hero hero : Hero.getAllHeroes()) {
                if (hero.getName().equalsIgnoreCase(heroName)) {
                    heroToRemove = hero;
                    break;
                }
            }
            foundSquad.removeMember(heroToRemove);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: get list of current members in squad
        get("/squads/:id/remove", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            model.put("members", foundSquad.getMembers());
            model.put("squad", foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "update.hbs");
        }, new HandlebarsTemplateEngine());



    }
}




