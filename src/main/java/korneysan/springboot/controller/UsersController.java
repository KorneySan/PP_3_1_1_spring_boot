package korneysan.springboot.controller;

import korneysan.springboot.model.User;
import korneysan.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
        prepareUsers();
    }

    //Подготовка заготовки базы
    private void prepareUsers() {
        List<User> userList = User.getUsersListDefault();
        for (User user: userList) {
            userService.create(user);
        }
    }

    // CREATE
    /* Подготовка экземпляра пользователя на форме */
    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "users/new";
    }

    /* Сохранение экземпляра пользователя в базу */
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        // параметры собираются сразу в модель User
        userService.create(user);
        return "redirect:users";
    }

    // READ
    /* Вывод всего списка пользователей */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "users/all";
    }

    /* Вывод одного пользователя */
    @GetMapping("/{id}")
    public String read(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }

    // UPDATE
    /* Подготовка экземпляра пользователя к изменению */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    /* Сохранение изменений в экземпляре пользователя в базу */
    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
