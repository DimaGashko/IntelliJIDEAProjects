package lib.Screen;

import lib.Router.Router;

import javax.persistence.EntityManager;

abstract public class ScreenController {
    protected Router router = new Router();
    protected EntityManager em;

    public void setRouter(Router router) {
        this.router = router;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

}
