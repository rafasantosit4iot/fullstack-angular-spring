import { Component, computed, effect, inject } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { GenreCreateBody } from '../../models/genre';
import { GenreService } from '../../services/genre.service';

@Component({
  selector: 'app-genre-form',
  imports: [ReactiveFormsModule],
  templateUrl: './genre-form.component.html',
  styleUrl: './genre-form.component.css'
})
export class GenreFormComponent {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private genreService = inject(GenreService);

  // SIGNALS
  public loading = computed(() => this.genreService.loading());
  public error = computed(() => this.genreService.error());
  public success = computed(() => this.genreService.success());
  public operationMessage = computed(() => this.genreService.operationMessage());

  // FORMULÁRIO
  public genreForm = this.formBuilder.group({
    name: ['', Validators.required],
    code: ['', Validators.required]
  });

  constructor() {
    effect(() => {
      const messages = this.operationMessage();
      if (messages.length > 0) {
        console.log(messages);
      }
      if (this.success()) {
        this.genreForm.reset();
      }
    });
  }

  onPageChange(newPagenumber: number) {
    this.genreService.setPageNumber(newPagenumber);
  }

  createGenre() {
    this.genreService.createGenre(this.genreForm.value as GenreCreateBody);
  }
}
