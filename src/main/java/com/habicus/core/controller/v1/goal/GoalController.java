/*
 _   _       _     _
| | | | __ _| |__ (_) ___ _   _ ___
| |_| |/ _` | '_ \| |/ __| | | / __|
|  _  | (_| | |_) | | (__| |_| \__ \
|_| |_|\__,_|_.__/|_|\___|\__,_|___/

 * This file is part of the Habicus Core Platform (https://github.com/Habicus/Habicus-Core).
 * Copyright (c) 2018 Habicus Core
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.habicus.core.controller.v1.goal;

import com.habicus.core.model.Goal;
import com.habicus.core.model.User;
import com.habicus.core.service.Goal.GoalService;
import com.habicus.core.service.User.UserService;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/goal")
public class GoalController {

  private static final Logger LOGGER = Logger.getLogger(GoalController.class.getName());

  private GoalService goalService;

  private UserService userService;

  @Autowired
  public void setGoalService(GoalService goalService) {
    this.goalService = goalService;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /**
   * Retrieves all goals that are associated with a particular {@link com.habicus.core.model.User}
   * given an input ID by the client
   *
   * @param id
   * @return
   */
  @RequestMapping(
    value = "/{id}",
    method = RequestMethod.GET,
    produces = {MediaType.APPLICATION_JSON}
  )
  public List<Goal> retrieveUserGoals(@PathVariable(value = "id") Long id) {
    LOGGER.info(String.format("Retrieving goals associated with user {}", id));
    return goalService.retrieveGoalsByUserId(id);
  }

  /**
   * This enables the ability for a particular user to create a {@link Goal} object from the client
   *
   * @return
   */
  @RequestMapping(
    value = "/{userId}",
    method = RequestMethod.POST,
    consumes = {MediaType.APPLICATION_JSON},
    produces = MediaType.APPLICATION_JSON
  )
  public Response addGoal(@PathVariable String userId, @RequestBody Goal inputGoal) {
    User user = userService.validateUserById(userId);
    if (user != null) {
      inputGoal.setUser(user);
      goalService.saveUserGoal(inputGoal);
      return Response.status(Status.OK).entity(inputGoal).build();
    }
    return Response.status(500, "User Not Found").build();
  }
}
