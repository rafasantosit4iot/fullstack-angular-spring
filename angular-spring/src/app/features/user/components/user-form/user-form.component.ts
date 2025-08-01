import { Component, computed, effect, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { UserCreateBody } from '../../models/user';

@Component({
  selector: 'app-user-form',
  imports: [ReactiveFormsModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private userService = inject(UserService);

  // SIGNALS
  public loading = computed(() => this.userService.loading());
  public error = computed(() => this.userService.error());
  public success = computed(() => this.userService.success());
  public operationMessage = computed(() => this.userService.operationMessage());

  // FORMULÁRIO
  public userForm = this.formBuilder.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    maxActiveLoans: [1, [Validators.min(1), Validators.max(5)]],
    active: [true],
  })

  constructor() {
    effect(() => {
      const messages = this.operationMessage();
      if (messages.length > 0) {
        console.log(messages);
      }
      if (this.success()) {
        this.userForm.reset();
      }
    });
  }

  onPageChange(newPagenumber: number) {
    this.userService.setPageNumber(newPagenumber);
  }

  createUser() {
    this.userService.createUser(this.userForm.value as UserCreateBody);
  }
}
