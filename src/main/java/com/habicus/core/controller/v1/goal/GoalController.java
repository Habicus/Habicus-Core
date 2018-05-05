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

import com.habicus.core.exception.API.InvalidRequestException;
import com.habicus.core.exception.NoGoalsFoundException;
import com.habicus.core.model.Goal;
import com.habicus.core.service.Goal.GoalService;
import com.habicus.core.service.User.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1")
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
   * Allows retrieval of user goals based on sec. token
   *
   * @return
   * @throws NoGoalsFoundException
   */
  @ExceptionHandler(NoGoalsFoundException.class)
  @GetMapping("/goals")
  public ResponseEntity<List<Goal>> retrieveUserGoals(Principal principal)
      throws NoGoalsFoundException {
    LOGGER.info("Current querying for all goals on user: " + principal.getName());

    int userId = userService.verifyAndRetrieveUser(principal);
    Optional<List<Goal>> goalList = goalService.retrieveGoalsByUserId(userId);
    if (!goalList.isPresent()) {
      throw new InvalidRequestException(
          String.format(
              "No goals found for requesting user: " + principal.getName() + ":" + userId),
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(goalList.get(), HttpStatus.OK);
  }
}
