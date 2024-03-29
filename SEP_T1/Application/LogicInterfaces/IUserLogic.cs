﻿using Domain.DTOs;
using Domain.Models;

namespace Application.LogicInterfaces;

public interface IUserLogic
{
    Task<UserReadDto> CreateAsync(UserCreationDto user);

    Task<IEnumerable<UserReadDto>> GetAllAsync();

    Task<UserReadDto?> GetByIdAsync(int id);

    Task DeleteAsync(int id);
    
    Task UpdateAsync(UserUpdateDto dto);

    Task UpdatePassword(UserNewPasswordDto newPassword);
}